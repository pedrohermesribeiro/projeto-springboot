package com.uploadfile.service;
import com.uploadfile.model.Arquivo;
import com.uploadfile.repository.ArquivoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
public class ArquivoService {

    private final ArquivoRepository arquivoRepository;

    public ArquivoService(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    public Arquivo salvarArquivo(MultipartFile file) throws IOException {
        String nome = file.getOriginalFilename();
        String tipo = file.getContentType();
        byte[] dados = file.getBytes();

        Arquivo arquivo = new Arquivo(nome, tipo, dados);
        return arquivoRepository.save(arquivo);
    }

    public Optional<Arquivo> buscarArquivo(Long id) {
        return arquivoRepository.findById(id);
    }
}

