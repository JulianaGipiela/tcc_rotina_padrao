package com.example.rotinapadrao

import java.util.Date

data class Preventiva(
    val data: String,
    val responsavel: String,
    val descricao: String,
    val inspecao: Boolean,
    val limpeza: Boolean,
    val limpezaExterna: Boolean,
    val muro: Boolean,
    val abrigo: Boolean,
    val telhado: Boolean,
    val sinal: Boolean,
    val nobreak: Boolean,
    val tensaobanco: String,
    val frequencia: String,
    val correntef3: String,
    val correntet: String,
    val correntef2: String,
    val correnten: String,
    val correntef1: String,
    val tensaont: String,
    val tensaofn: String,
    val tesaof3f1: String,
    val tensaof2f3: String,
    val tensaof1f2: String,
    val site: String
)
