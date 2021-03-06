package com.mballem.curso.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.boot.dao.FuncionarioDao;
import com.mballem.curso.boot.domain.Funcionario;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao dao;

	@Override
	public void salvar(com.mballem.curso.boot.domain.Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Override
	public void editar(com.mballem.curso.boot.domain.Funcionario funcionario) {
		dao.update(funcionario);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public com.mballem.curso.boot.domain.Funcionario buscaPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<com.mballem.curso.boot.domain.Funcionario> buscarTodos() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscaPorNome(String nome) {
		return dao.findByNome(nome);
	}

	@Override
	public List<Funcionario> buscaPorCargo(Long id) {

		return dao.findByCargoId(id);
	}

	@Override
	public List<Funcionario> buscaPorDatas(LocalDate entrada, LocalDate saida) {
		if (entrada != null && saida != null) {
			return dao.findByDataEntradaDataSaida(entrada, saida);
		} else if (entrada != null) {
			return dao.findByDataEntrada(entrada);
		} else if (saida != null) {
			return dao.findByDataSaida(saida);
		} else {
			return new ArrayList<>();
		}

	}

}
