//
//  CategoryList.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/10.
//

import Foundation

struct CategoryListResponse: Codable {
    var statusCode: Int
    var responseMessage: String
    var data: [CategoryList]
}

struct CategoryList: Codable {
    var shopIdx: Int //place idx || reservationIdx
    var reservationIdx: Int
    var date: String?
    var time: String?
    var thumbnail: String?
    var title: String?
    var address: String?
    
    enum CodingKeys: String, CodingKey {
        case shopIdx = "idx"
        case reservationIdx = "reservation_idx"
        case date, time, thumbnail, title, address
    }
}
