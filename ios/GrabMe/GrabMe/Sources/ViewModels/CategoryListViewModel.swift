//
//  CategoryListViewModel.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/10.
//

import UIKit

class CategoryListViewModel {
    //MARK: - Properties
    var days = [Int]()
    var daysInText = [String]()
    
    private let manager = CategoryListManager.shared
    
    var categoryList: [CategoryList] {
        return manager.categoryList
    }
    
    var categoryDetail: CategoryListDetail {
        return manager.categoryDetail!
    }
    
    func requestList(category: Int, lat: Double, long: Double, completion: @escaping() -> Void) {
        manager.requestList(category: category, lat: lat, long: long, completion: {
           // print("🔵🔵\(category), \(lat), \(long)")
            completion()
        })
    }
    
    func requestPlaceDetail(shopIdx: String, completion: @escaping() -> Void) {
        
        manager.requestPlaceDetail(shopIdx: shopIdx, completion: {
            print("🟡got it")
            completion()
        })
    }
    
    func sevendaysAfterToday() {
        //코드 수정하기!
        let cal = Calendar.current
        var date = cal.startOfDay(for: Date())
 
        let dateformatter = DateFormatter()
        dateformatter.dateFormat = "EE"
        for i in 1 ... 7 {
            let day = cal.component(.day, from: date)
            days.append(day)
            daysInText.append(dateformatter.string(from: date))
            date = cal.date(byAdding: .day, value: +1, to: date)!
        }
    }
    
    
}
enum Categorysss: Int {
    case karaoke = 1
    case hospital = 2
    case cafe = 3
    case gym = 4
    case education = 5
    case beauty = 6
    case restaurant = 7
    
    func getCategory() -> String {
        switch self {
        case .karaoke: return "karaoke"
        case .hospital: return "hospital"
        case .cafe: return "cafe"
        case .gym: return "gym"
        case .education: return "education"
        case .beauty: return "beauty"
        case .restaurant: return "restaurant"
        }
    }
}
