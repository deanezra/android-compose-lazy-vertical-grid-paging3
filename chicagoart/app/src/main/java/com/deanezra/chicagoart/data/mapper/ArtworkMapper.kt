package com.deanezra.chicagoart.data.mapper

import com.deanezra.chicagoart.data.remote.ArtworkDto
import com.deanezra.chicagoart.domain.model.Artwork

/* Mapper extension for mapping ArtworkDto to Artwork domain model
 * Only the image_id really needs special mapping attention as its is mapped onto
 * imageFullUrl which uses the IIIF Image API 2.0 specification to refer to actual
 * image binary
 *
 * This means that each image is referred to using only its image_id string in parent
 * objects like Artwork, then the full url to the image is based on following format:
 *
 * baseurl + /iiif/2/{identifier}/full/843,/0/default.jpg
 * where baseurl is: https://api.chicagoart.org/
 *
 * This conversion will be done by the mapper when converting from DTO to Domain object.
 */
fun ArtworkDto.toArtwork(): Artwork {

    val IMAGE_BASE_URL = "https://www.artic.edu/iiif/2/"
    val SMALL_IMAGE_POSTFIX = "/full/200,/0/default.jpg"
    val LARGE_IMAGE_POSTFIX = "/full/843,/0/default.jpg"

    return Artwork(
        id = id,
        title = title,
        date = date_display,
        artist = artist_display,
        medium = medium_display,
        type = artwork_type_title,
        imageId = image_id,

        smallImageUrl = "${IMAGE_BASE_URL}${image_id}${SMALL_IMAGE_POSTFIX}",
        largeImageUrl = "${IMAGE_BASE_URL}${image_id}${LARGE_IMAGE_POSTFIX}",
    )
}
