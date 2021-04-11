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
            print("\(data)")
            guard let decodeData = self.decode(CategoryListResponse.self, data: data) else { return }
            print("🔴 \(decodeData.statusCode), \(decodeData.responseMessage)")
            completion(decodeData.data)
        }
        
    }
    
    func searchPlace(category: Int, placeName: String) {
        
    }
}
