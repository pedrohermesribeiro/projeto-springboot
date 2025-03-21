package com.uploadfile.controller;
import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uploadfile.model.Arquivo;
import com.uploadfile.service.ArquivoService;

@RestController
@RequestMapping("/arquivos")
@CrossOrigin(origins = "*")  // Permite chamadas externas (CORS)
public class ArquivoController {

    private final ArquivoService arquivoService;

    public ArquivoController(ArquivoService arquivoService) {
        this.arquivoService = arquivoService;
    }

    // ✅ Endpoint para Upload de Arquivo
    @PostMapping("/upload")
    public ResponseEntity<String> uploadArquivo(@RequestParam("file") MultipartFile file) {
        try {
            Arquivo arquivo = arquivoService.salvarArquivo(file);
            return ResponseEntity.ok("Arquivo salvo com sucesso! ID: " + arquivo.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar arquivo");
        }
    }

    // ✅ Endpoint para Download de Arquivo pelo ID
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable Long id) {
        Optional<Arquivo> arquivoOptional = arquivoService.buscarArquivo(id);

        if (arquivoOptional.isPresent()) {
            Arquivo arquivo = arquivoOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
                    .body(arquivo.getDados());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

