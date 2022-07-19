package com.ahinfo.ahteam.core.navigation

import com.ahinfo.ahteam.R

enum class DestinationsParser(val id : Int) {
    SPLASH_TO_AUTH(R.id.action_splashFragment_to_authorizationFragment),
    SPLASH_TO_SECTIONS(R.id.action_splashFragment_to_sectionsFragment),

    SECTIONS_TO_LIST_PROJECTS(R.id.action_sectionsFragment_to_listProjectsFragment),

    LIST_PROJECTS_TO_DETAIL_PROJECT(R.id.action_listProjectsFragment_to_detailProjectFragment),
    LIST_PROJECTS_TO_ADD_PROJECT(R.id.action_listProjectsFragment_to_addProjectFragment),
    LIST_PROJECT_TO_UPGRADE_PROJECT(R.id.action_listProjectsFragment_to_updateProjectFragment),
    LIST_PROJECT_TO_SECTIONS_APP(R.id.action_listProjectsFragment_to_sectionsFragment),

    DETAIL_PROJECT_TO_ADD_TASK(R.id.action_detailProjectFragment_to_addTaskFragment),
    DETAIL_PROJECT_TO_LIST_PROJECTS(R.id.action_detailProjectFragment_to_listProjectsFragment),

    ADD_PROJECT_TO_LIST_PROJECTS(R.id.action_addProjectFragment_to_listProjectsFragment),

    UPDATE_PROJECT_TO_LIST_PROJECTS(R.id.action_updateProjectFragment_to_listProjectsFragment)
}