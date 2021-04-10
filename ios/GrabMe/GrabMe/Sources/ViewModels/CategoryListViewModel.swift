//
//  CategoryListViewModel.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/10.
//

import UIKit

class CategoryListViewModel {
    //MARK: - Properties
    
    private let manager = CategoryListManager.shared
    
    var categoryList: [CategoryList] {
        return manager.categoryList
    }
    
    func requestList(category: Int, lat: Double, long: Double, completion: @escaping() -> Void) {
        manager.requestList(category: category, lat: lat, long: long, completion: {
            print("🔵🔵\(self.categoryList.count)")
            
            completion()
        })
    }
    
    
}
