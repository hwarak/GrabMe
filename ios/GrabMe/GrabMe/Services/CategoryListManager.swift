//
//  categoryListManager.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/08.
//

import UIKit

class CategoryListManager {
    //MARK: - Properties
    static let shared = CategoryListManager()
    
    private var x,y: Double?
    private var categoryIdx: Int?
    private var currentpage = 0
    var categoryList: [CategoryList] = []
    var categoryDetail: CategoryListDetail?
    
    
    //MARK: - Actions
    func retriveCategoryList(category: Int, lat: Double, long: Double) {
        
    }
    func searchPlace(category: Int, placeName: String) {
        // x ,y 를 받아서 가까운위치로부터 알려주낭? 
    }
    
    func requestList(category: Int, lat: Double, long: Double, completion: @escaping() -> Void) {
        CategoryListServices.retrieveCategoryList(category: category, lat: lat, long: long, completion: { data in
            self.categoryList = data
            
            print("\(self.categoryList.count)")
            completion()
        })
    }
    
    func requestPlaceDetail(shopIdx: String, completion: @escaping() -> Void) {
        CategoryListServices.requestPlaceDetail(shopIdx: shopIdx, completion: { data in
           // print("♥️\(shopIdx)")
            self.categoryDetail = data
            completion()
        })
        
    }
    
}

