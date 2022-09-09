package com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.mapper

import com.ahinfo.ahteam.core.bases.Mapper
import com.ahinfo.ahteam.data.parser.catalogElementsLinks.remote.dto.response.ResponseElementsLinksFilter
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.*
import javax.inject.Inject

class ElementsLinksFilterDataToDomainMapper @Inject constructor() :
    Mapper<ResponseElementsLinksFilter, ElementsLinksFilterDomain> {
    override fun map(data: ResponseElementsLinksFilter): ElementsLinksFilterDomain {

        val rangeDomain = RangeDomain(
            min = data.filters?.price?.range?.min,
            max = data.filters?.price?.range?.max,
        )

        val price = PriceDomain(
            name = data.filters?.price?.name,
            range = rangeDomain,
            title = data.filters?.price?.title,
            type = data.filters?.price?.type
        )

        val existDomain = ExistDomain(
            name = data.filters?.exist?.name,
            title = data.filters?.exist?.title,
            type = data.filters?.exist?.type,
            value = data.filters?.exist?.value
        )

        val listValuesItemDomain = mutableListOf<ValuesItemDomain>().apply {
            data.filters?.sectionId?.values?.forEach {
                add(
                    ValuesItemDomain(
                        it?.sectionId,
                        it?.name
                    )
                )
            }
        }

        val sectionId = SectionIdDomain(
            values = listValuesItemDomain,
            name = data.filters?.sectionId?.name,
            title = data.filters?.sectionId?.title,
            type = data.filters?.sectionId?.type
        )

        val filters = FiltersDomain(
            exist = existDomain,
            sectionId = sectionId,
            price = price
        )

        return ElementsLinksFilterDomain(filters)
    }
}