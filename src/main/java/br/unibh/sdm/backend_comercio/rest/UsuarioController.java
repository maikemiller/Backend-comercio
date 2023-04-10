package br.unibh.sdm.backend_comercio.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.backend_comercio.entidades.Usuario;
import br.unibh.sdm.backend_comercio.negocio.UsuarioService;

/**
 * Classe contendo as definições de serviços REST/JSON para Criptomoeda
 * 
 * @author jhcru
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping(value = "{id}")
    public Usuario getUsuarioById(@PathVariable String id) throws Exception {
        if (ObjectUtils.isEmpty(id)) {
            throw new Exception("Usuario com id " + id + " nao encontrada");
        }

        return usuarioService.getUsuarioById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario createUsuario(@RequestBody @NotNull Usuario usuario) throws Exception {
        return usuarioService.saveUsuario(usuario);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario updateUsuario(@PathVariable String id, @RequestBody @NotNull Usuario usuario) throws Exception {
        if (!id.equals(usuario.getId())) {
            throw new Exception("Usuario " + id + " nao está correto");
        }

        return usuarioService.saveUsuario(usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateUsuario(@PathVariable String id) throws Exception {
        usuarioService.deleteUsuarioById(id);
        return true;
    }

}