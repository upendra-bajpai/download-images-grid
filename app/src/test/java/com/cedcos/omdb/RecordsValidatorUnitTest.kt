package com.cedcos.omdb

import com.cedcos.omdb.data.model.ResponseModel
import com.cedcos.omdb.data.repository.ImageRepository
import com.cedcos.omdb.viewModels.SequenceViewModel
import io.mockk.mockk
import org.junit.Test
import org.junit.Before

class RecordsValidatorUnitTest {
    lateinit var viewModel: SequenceViewModel

    @Before
    fun setUp() {
        var calculatorService = mockk<ImageRepository>()
        viewModel = SequenceViewModel(calculatorService)
    }

    @Test
    fun `Assert product final price`() {
        var response:ArrayList<ResponseModel> = ArrayList<ResponseModel>()
        response.add(ResponseModel("Test",100.0,""))
        response.add(ResponseModel("test no capital letter ",100.0,""))
        val finalResult = viewModel.conditionalFilter(response)

        // When the product [basePrice] is 1, we expect the
       System.out.println(finalResult)
    }
}
