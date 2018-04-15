package me.w2x.demo.common

import org.apache.commons.lang3.StringUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

/**
 * Created by Charles Chen on 01/03/2018.
 */
class Pageable(
        var page: Int = 0,
        var size: Int = 10,
        var sortName: String = "id",
        var sortOrder: String = "DESC"
)

fun String.equalsIgnoreCase(str: String): Boolean {
    return StringUtils.equalsIgnoreCase(this, str)
}


fun Pageable.toPageRequest(): PageRequest {
    val direction: Sort.Direction = if (sortOrder.equalsIgnoreCase("DESC")) Sort.Direction.DESC else Sort.Direction.ASC
    return PageRequest(page, size, Sort(direction, sortName))
}