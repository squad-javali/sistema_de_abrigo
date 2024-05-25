package com.compass.application.services;

import java.sql.SQLException;
import java.util.List;

import com.compass.application.dao.AbrigoDAO;
import com.compass.application.entities.Abrigo;

public class AbrigoService {

    private AbrigoDAO abrigoDAO;


    public AbrigoService(AbrigoDAO abrigoDAO) {
        this.abrigoDAO = abrigoDAO;
    }

    public void cadastrarAbrigo(Abrigo abrigo) throws SQLException {
        if (abrigoValido(abrigo)) {
            abrigoDAO.insertAbrigo(abrigo);
        }
    }

    public Abrigo buscarAbrigoPorId(int id) {
        return abrigoDAO.selectAbrigo(id);
    }

    public List<Abrigo> listarTodosAbrigos() {
        return abrigoDAO.selectAllAbrigos();
    }

    public boolean atualizarAbrigo(Abrigo abrigo) throws SQLException {
        if (abrigoValido(abrigo)) {
            return abrigoDAO.updateAbrigo(abrigo);
        }
        return false;
    }

    public boolean deletarAbrigo(int id) throws SQLException {
        return abrigoDAO.deleteAbrigo(id);
    }

    private boolean abrigoValido(Abrigo abrigo) {
        return true;
    }

}


