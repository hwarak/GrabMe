//
//  CategoryListServices.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/10.
//

import Foundation

class CategoryListServices: Networkable {
    static func retrieveCategoryList(category: Int, lat: Double, long: Double, completion: @escaping ([CategoryList]) -> Void) {
        
        reqeustAPI(request: Networking.requestObject(type: .categoryList(x: "\(long)" ,y: "\(lat)", categoryIdx: "\(category)", page: "0" ), requestType: .get)) { ( data ) in
           // print("\(data)")
            guard let decodeData = self.decode(CategoryListResponse.self, data: data) else { return }
            print("🔴 response: \(decodeData.statusCode), \(decodeData.responseMessage)")
            completion(decodeData.data)
        }
        
    }
    
    static func searchPlace(category: Int, placeName: String) {

        
    }
    
    static func requestPlaceDetail(shopIdx: String, completion: @escaping(CategoryListDetail) -> Void) {
        reqeustAPI(request: Networking.requestObject(type: .shopDetail(shopIdx: shopIdx), requestType: .get), completion: { data in
            
            guard let decodeData = self.decode(CategoryDetailResponse.self, data: data) else { return }
            print("🔴 response: \(decodeData.statusCode), \(decodeData.responseMessage)")
            
            completion(decodeData.data)
        })
    }
}
