package com.ahinfo.ahteam.data.parser.currentParserProject.local

enum class ParserStatuses(val status : String) {
    PARSING_CREATE("parsing_create"),
    MENU_START("menu_start"),
    MENU_COMPLETE("menu_complete"),
    CATALOG_START("catalog_start"),
    CATALOG_COMPLETE("catalog_complete"),
    ELEMENT_START("element_start"),
    ELEMENT_COMPLETE("element_complete"),
    MENU_ERROR("menu_error"),
    CATALOG_ERROR("catalog_error"),
    ELEMENT_ERROR("element_error")
}