package me.w2x.demo.aop

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors

/**
 * Created by Charles Chen on 27/02/2018.
 */
@ControllerAdvice
class CustomResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders?, status: HttpStatus?, request: WebRequest?): ResponseEntity<Any> {
        val bindingResult = ex.bindingResult

        val fieldErrors = bindingResult.fieldErrors.stream().map {
            mapOf<String, Any?>("field" to it.field, "code" to it.code, "rejectedValue" to it.rejectedValue)
        }.collect(Collectors.toList())

        val globalErrors = bindingResult.globalErrors.stream().map {
            mapOf<String, Any?>("code" to it.code)
        }.collect(Collectors.toList())

        val errorView = mapOf("fieldErrors" to fieldErrors, "globalErrors" to globalErrors)
        return ResponseEntity(errorView, HttpStatus.UNPROCESSABLE_ENTITY)
    }
}