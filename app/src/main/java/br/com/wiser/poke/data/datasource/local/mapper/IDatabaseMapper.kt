package br.com.wiser.poke.data.datasource.local.mapper

interface IDatabaseMapper<S, T> : FromDatabaseMapper<S, T>, ToDatabaseMapper<S, T>