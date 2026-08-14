package com.guilherme.api13_08_2026;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public Mensagem hello() {
        //return "Hello World";
    	return new Mensagem("Hello world");
    }
    
    @GetMapping("/apresentar")
    public Apresentacao apresentacao(@RequestParam String nome, @RequestParam int idade) {
        return new Apresentacao(nome, idade);
    }
    
    @GetMapping("/ola/{nome}")
    public Mensagem ola(@PathVariable String nome) {
        return new Mensagem("Olá " + nome);
    }
    
    @GetMapping("/saudacao")
    public Mensagem saudacao(@RequestParam String nome) {
        return new Mensagem("Olá " + nome);
    }
    
    @GetMapping("/triplo/{numero}")
    public int dobro(@PathVariable int numero) {
        return numero * 3;
    }
    
    @GetMapping("/somar")
    public int somar(
            @RequestParam int numero1,
            @RequestParam int numero2) {

        return numero1 + numero2;
    }
    
    //POST
    
    @PostMapping("/apresentar")
    public Apresentacao apresentarPost(@RequestBody Apresentacao apresentacao) {
        return apresentacao;
    }
    
    
    
    

}