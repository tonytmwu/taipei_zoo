package com.net.taipeizoo.model

data class ZooAnimalApiResult(
        val limit: Int = 0,
        val offset: Int = 0,
        val count: Int = 0,
        val sort: String? = null,
        val results: List<ZooAnimal>? = null
)