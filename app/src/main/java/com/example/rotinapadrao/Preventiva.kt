package com.example.rotinapadrao

import java.util.Date

data class Preventiva(
    val data: String,
    val responsavel: String,
    val descricao: String,
    val inspecao: Boolean,
    val limpeza: Boolean,
    val limpezaExterna: Boolean,
    val site: String
)
