package com.fatec.avalia.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class CloudStorageService {

    @Value("${storage.url}")
    private String storageUrl;

    @Value("${storage.key}")
    private String storageKey;

    @Value("${storage.bucket}")
    private String storageBucket;

    private final RestTemplate restTemplate;

    public CloudStorageService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String subirArquivo(MultipartFile arquivo) {
        try {
            String nomeOriginal = arquivo.getOriginalFilename();
            String extensao = (nomeOriginal != null && nomeOriginal.contains("."))
                    ? nomeOriginal.substring(nomeOriginal.lastIndexOf(".")) : "";
            String nomeArquivoFinal = UUID.randomUUID().toString() + extensao;

            String urlUpload = storageUrl + "/storage/v1/object/" + storageBucket + "/" + nomeArquivoFinal;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + storageKey);
            headers.set("apikey", storageKey);
            headers.setContentType(MediaType.parseMediaType(arquivo.getContentType()));

            HttpEntity<byte[]> entity = new HttpEntity<>(arquivo.getBytes(), headers);
            ResponseEntity<String> response = restTemplate.exchange(urlUpload, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return storageUrl + "/storage/v1/object/public/" + storageBucket + "/" + nomeArquivoFinal;
            } else {
                throw new RuntimeException("Falha ao enviar arquivo para o storage remoto.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo: " + e.getMessage());
        }
    }

    /**
     * Remove o arquivo do storage remoto.
     * @param urlCompleta A URL pública salva no banco de dados.
     */
    public void deletarArquivo(String urlCompleta) {
        if (urlCompleta == null || urlCompleta.isEmpty()) return;

        try {
            // Extrai apenas o nome do arquivo da URL (tudo que vem após a última '/')
            String nomeArquivo = urlCompleta.substring(urlCompleta.lastIndexOf("/") + 1);

            // Endpoint de exclusão: {URL}/storage/v1/object/{BUCKET}/{NOME}
            String urlDelete = storageUrl + "/storage/v1/object/" + storageBucket + "/" + nomeArquivo;

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + storageKey);
            headers.set("apikey", storageKey);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            // Envia o DELETE para a API do Storage
            restTemplate.exchange(urlDelete, HttpMethod.DELETE, entity, String.class);

        } catch (Exception e) {
            // Logamos o erro mas não travamos o sistema, pois o registro no banco já pode ter ido embora
            System.err.println("Aviso: Não foi possível deletar o arquivo no storage: " + e.getMessage());
        }
    }
}