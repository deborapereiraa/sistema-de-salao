package com.deborapereira.salaodebeleza.mensagem;

import org.springframework.stereotype.Component;

@Component
public class Client {

    public void enviarMensagem (String numeroTelefone, String mensagem){
        System.out.println( "----------- [WHATSAPP]----------- ");
        System.out.println("Enviando para: " + numeroTelefone);
        System.out.println("Texto da mensagem: " + mensagem);
        System.out.println("--------------------------------");
    }

}
