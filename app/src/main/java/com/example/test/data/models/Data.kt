package com.example.test.data.models

data class Data(val a: Int)


data class PROFILE(
    var MATRIID: String,
    var MASKEDMATRIID: String,
    var PROFILESTATUS: String,
    var PRIVILAGE: String,
    var PACKAGETYPE: String,
    var NAME: String,
    var GENDER: String,
    var ONLINESTATUS: String,
    var ONLINEFLAG: String,
    var PROFILEHIGHLIGHTER: String,
    var PROFILECREATEDBY: String,
    var MARITALSTATUS: String,
    var NOOFCHILDREN: String,
    var CHILDRENLIVINGSSTATUS: String,
    var AGE: String,
    var HEIGHT: String,
    var RELIGION: String,
    var SUBCASTE: String,
    var DENOMINATION: String,
    var CITY: String,
    var STATE: String,
    var COUNTRY: String,
    var EDUCATION: String,
    var OCCUPATION: String,
    var LASTLOGIN: String,
    var PUBLISH: String,
    var PAIDSTATUS: String,
    var MIMASCORES: String,
    var PHONEVERIFIED: String,
    var PHOTOAVAILABLE: String,
    var PHOTOPROTECTED: String,
    var PHOTOSTATUS: String,
    var COUNT: String,
    var PHOTOREQUEST: String,
    var THUMBNAME: String,
    var THUMBBIG: String,
    var THUMBMEDIUM: String,
    var ALLPHOTOS: String,
    var PHOTOPATH: String,
    var ANNUALINCOME: String,
    var SOIC: String,
    var MSGINT: String,
    var ISMASK: String,
    var PROFILESHORTLISTED: String,
    var LASTCOMMUNICATION: String,
    var MARKASVIEWED: String
)

data class SEARCHRES(val PROFILE: List<PROFILE>)
data class SearchResultsModel(
    var RESPONSECODE: String,
    var ERRORDESC: String,
    var TOTALRESULTS: String,
    var VIEWPROFILERANDOMNO: String,
    var SRCHTIME: String,
    var SEARCHRES: SEARCHRES,
    var SEARCHCONDITION: String,
    var PAGE: String,
    var MODULETYPE: String
)

data class ProgressHandler(val isProgress: Boolean, val message: String)
data class ErrorHandler(val reqType: String, val errorMsg: Any)

