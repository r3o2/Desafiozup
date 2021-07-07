package com.ComicsApi.DesafioZUPComics.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

@SuppressWarnings("serial")
public class CadastroUsuarioDto implements Serializable {

    @NotBlank(message = "O campo CPF é obrigatório.")
    @NotNull(message = "O campo CPF não pode ser nulo.")
    @Pattern(regexp = "[\\d]{11}", message = "CPF inválido.")
    private String cpf;

    @NotBlank(message = "O campo NOME é obrigatório.")
    @NotNull(message = "O campo NOME não pode ser nulo.")
    @Size(max = 50, message = "O tamanho máximo para o NOME é 50 caracteres.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "O nome não pode conter números e caracteres especiais.")
    private String nome;

    @NotBlank(message = "O campo E-MAIL é obrigatório.")
    @NotNull(message = "O campo E-MAIL não pode ser nulo.")
    @Size(max = 50, message = "O tamanho máximo para o EMAIL é 50 caracteres.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "O campo Nascimento é obrigatório.")
    @NotNull(message = "O campo Nascimento não pode ser nulo.")
    @Pattern(regexp = "(^(((0[1-9]|1[0-9]|2[0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))" +
            "|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\\d\\d$)|(^29[-]02[-](19|[2-9][0-9])" +
            "(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)",
            message = "A data de nascimento deve ser informada no formato dd-MM-yyyy e deve ser uma data válida.")
    private String nascimento;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

    //Getters
   
}
