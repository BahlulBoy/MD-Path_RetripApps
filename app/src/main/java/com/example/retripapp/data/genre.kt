package com.example.retripapp.data

data class Genre(
    val img : String,
    val text : String
)

val genreList = listOf<Genre>(
    Genre(
        img = "https://i2-prod.hertfordshiremercury.co.uk/incoming/article7604344.ece/ALTERNATES/s1200c/0_PICTURED-The-final-Firework-Champions-of-2022-is-set-to-be-an-explosive-affair.jpg",
        text = "Event"
    ),
    Genre(
        img = "https://www.cimbniaga.co.id/content/dam/cimb/inspirasi/5-destinasi-wisata-budaya-populer-di-indonesia.webp",
        text = "Destination"
    ),
    Genre(
        img = "https://www.thesun.co.uk/wp-content/uploads/2022/06/NINTCHDBPICT000719603839.jpg",
        text = "Theme\nPark"
    ),
    Genre(
        img = "https://bobobox.com/blog/wp-content//uploads/elementor/thumbs/wisata-religi-mojokerto-q6kxk6lymo4vsacane3chp6b4ancysad5jujng1k2s.jpg",
        text = "Religion"
    ),
    Genre(
        img = "https://www.jatengnews.id/wp-content/uploads/2023/03/R-11.jpg",
        text = "Street\nFood"
    ),
    Genre(
        img = "https://s-light.tiket.photos/t/01E25EBZS3W0FY9GTG6C42E1SE/rsfit19201280gsm/events/2022/08/08/99b57870-0b67-4e9c-8ca9-f7b1705df066-1659926196077-f80c155b509813125037322d2d9ce418.jpg",
        text = "Water Park"
    ),
    Genre(
        img = "https://cdn.vox-cdn.com/thumbor/5d_RtADj8ncnVqh-afV3mU-XQv0=/0x0:1600x1067/1200x900/filters:focal(672x406:928x662)/cdn.vox-cdn.com/uploads/chorus_image/image/57698831/51951042270_78ea1e8590_h.7.jpg",
        text = "Restaurant"
    ),
    Genre(
        img = "https://www.pelajaran.co.id/wp-content/uploads/2018/10/Museum.jpg",
        text = "Museum"
    ),
)

var event = listOf<String>(
    "https://api2.kemenparekraf.go.id/storage/app/uploads/public/63b/7e8/dc5/63b7e8dc5ff0b911243103.jpg",
    "https://seru.co.id/wp-content/uploads/2023/02/WhatsApp-Image-2023-02-14-at-11.28.41.jpg"
)