//
//  CategoryDetail.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/11.
//

import Foundation

struct CategoryDetailResponse: Codable {
    var statusCode: Int
    var responseMessage: String
    var data: CategoryListDetail
}

struct CategoryListDetail: Codable {
    var shopIdx: Int
    var ownerIdx: Int
    var categoryIdx: Int
    var thumbnail: String
    var title: String
    var address: String
    var phone: String
    var description: String?
    var kakaoURL: String?
    var instaURL: String?
    
    enum CodingKeys: String, CodingKey {
        case shopIdx = "idx"
        case ownerIdx = "owner_idx"
        case categoryIdx = "category_idx"
        case description = "introduction"
        case kakaoURL = "openkatalkURL"
        case thumbnail,title,address,phone,instaURL
    }

}

struct ReservationStatusResponse: Codable {
    var statusCode: Int
    var responseMessage: String
    var data: [ReservationStatus]
}

struct ReservationStatus: Codable {
    var idx: Int
    var shop_idx: Int
}
