package br.com.ucsal.autenticacaoservice.dtos;

public record ApiResponse<T>(
        int status,
        String message,
        T data
) {
}