package com.murmur.murmur.utils

import com.bookmanagerdb.bookmanagerdb.entity.dto.PagesDTO
import org.springframework.data.domain.Page
import kotlin.math.ceil

object DTOUtil {

    fun <T> pagesToPagesDTO(pages: Page<T>): PagesDTO<T> {
        return PagesDTO(
            hasMore = pages.hasNext(),
            totalPages = pages.totalPages,
            totalElements = pages.totalElements,
            currentPage = pages.number + 1,
            currentElements = pages.numberOfElements,
            pageSize = pages.size,
            isFirst = pages.isFirst,
            isLast = pages.isLast,
            hasPrevious = pages.hasPrevious(),
            dataList = pages.content
        )
    }

    fun <T> listToPageDTO(pageList: List<T>, page: Int, size: Int): PagesDTO<T> {
        val currentElements = if (page * size <= pageList.size) size else if (page == 1 && pageList.size < size) pageList.size else pageList.size % size
        val totalPages = ceil(pageList.size / size.toDouble()).toInt()
        return PagesDTO(
            hasMore = page * size < pageList.size,
            totalPages = totalPages,
            totalElements = pageList.size.toLong(),
            currentPage = page,
            currentElements = currentElements,
            pageSize = size,
            isFirst = page == 1,
            isLast = page == totalPages,
            hasPrevious = page != 1,
            dataList = pageList
        )
    }
}