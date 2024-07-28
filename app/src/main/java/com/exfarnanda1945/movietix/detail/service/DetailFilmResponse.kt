package com.exfarnanda1945.movietix.detail.service

import com.squareup.moshi.Json

data class DetailFilmResponse(

    @Json(name= "original_language")
    var originalLanguage: String? = "",

    @Json(name= "imdb_id")
    var imdbId: String? = "",

    @Json(name= "videos")
    var videos: Videos? = Videos(),

    @Json(name= "video")
    var video: Boolean? = false,

    @Json(name= "title")
    var title: String? = "",

    @Json(name= "backdrop_path")
    var backdropPath: String? = "",

    @Json(name= "revenue")
    var revenue: Int? = 0,

    @Json(name= "genres")
    var genres: List<GenresItem?>? = emptyList(),

    @Json(name= "popularity")
    var popularity: Double? = 0.0,

    @Json(name= "production_countries")
    var productionCountries: List<ProductionCountriesItem?>? = emptyList(),

    @Json(name= "id")
    var id: Int? = 0,

    @Json(name= "vote_count")
    var voteCount: Int? = 0,

    @Json(name= "budget")
    var budget: Int? = 0,

    @Json(name= "overview")
    var overview: String? = "",

    @Json(name= "images")
    var images: Images? = Images(),

    @Json(name= "original_title")
    var originalTitle: String? = "",

    @Json(name= "runtime")
    var runtime: Int? = 0,

    @Json(name= "poster_path")
    var posterPath: String? = "",

    @Json(name= "origin_country")
    var originCountry: List<String?>? = emptyList(),

    @Json(name= "spoken_languages")
    var spokenLanguages: List<SpokenLanguagesItem?>? = emptyList(),

    @Json(name= "production_companies")
    var productionCompanies: List<ProductionCompaniesItem?>? = emptyList(),

    @Json(name= "release_date")
    var releaseDate: String? = "",

    @Json(name= "vote_average")
    var voteAverage: Double? = 0.0,

    @Json(name= "belongs_to_collection")
    var belongsToCollection: BelongsToCollection? = BelongsToCollection(),

    @Json(name= "tagline")
    var tagline: String? = "",

    @Json(name= "adult")
    var adult: Boolean? = false,

    @Json(name= "homepage")
    var homepage: String? = "",

    @Json(name= "status")
    var status: String? = ""
)

data class ProductionCountriesItem(

    @Json(name= "name")
    var name: String? = ""
)

data class Images(

    @Json(name= "backdrops")
    var backdrops: List<BackdropsItem?>? = emptyList(),

    @Json(name= "posters")
    var posters: List<PostersItem?>? = emptyList(),
)

data class BackdropsItem(

    @Json(name= "aspect_ratio")
    var aspectRatio: Double? = 0.0,

    @Json(name= "file_path")
    var filePath: String? = "",

    @Json(name= "vote_average")
    var voteAverage: Double? = 0.0,

    @Json(name= "width")
    var width: Int? = 0,


    @Json(name= "vote_count")
    var voteCount: Int? = 0,

    @Json(name= "height")
    var height: Int? = 0
)

data class PostersItem(

    @Json(name= "aspect_ratio")
    var aspectRatio: Double? = 0.0,

    @Json(name= "file_path")
    var filePath: String? = "",

    @Json(name= "vote_average")
    var voteAverage: Double? = 0.0,

    @Json(name= "width")
    var width: Int? = 0,


    @Json(name= "vote_count")
    var voteCount: Int? = 0,

    @Json(name= "height")
    var height: Int? = 0
)

data class ResultsItem(

    @Json(name= "site")
    var site: String? = "",

    @Json(name= "size")
    var size: Int? = 0,

    @Json(name= "name")
    var name: String? = "",

    @Json(name= "official")
    var official: Boolean? = false,

    @Json(name= "id")
    var id: String? = "",

    @Json(name= "type")
    var type: String? = "",

    @Json(name= "published_at")
    var publishedAt: String? = "",

    @Json(name= "key")
    var key: String? = ""
)

data class GenresItem(

    @Json(name= "name")
    var name: String? = "",

    @Json(name= "id")
    var id: Int? = 0
)

data class ProductionCompaniesItem(

    @Json(name= "logo_path")
    var logoPath: String? = "",

    @Json(name= "name")
    var name: String? = "",

    @Json(name= "id")
    var id: Int? = 0,

    @Json(name= "origin_country")
    var originCountry: String? = ""
)

data class Videos(

    @Json(name= "results")
    var results: List<ResultsItem?>? = emptyList()
)

data class BelongsToCollection(

    @Json(name= "backdrop_path")
    var backdropPath: String? = "",

    @Json(name= "name")
    var name: String? = "",

    @Json(name= "id")
    var id: Int? = 0,

    @Json(name= "poster_path")
    var posterPath: String? = ""
)

data class SpokenLanguagesItem(

    @Json(name= "name")
    var name: String? = "",


    @Json(name= "english_name")
    var englishName: String? = ""
)
