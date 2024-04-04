package com.eloinavarro.holocron.data.retrofit

import com.eloinavarro.holocron.data.Mapper

abstract class RetrofitDatasource<I, O> constructor(mapper: Mapper<I, O>)