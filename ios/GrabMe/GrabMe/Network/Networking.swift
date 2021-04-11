//
//  EndPoint.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import Foundation

class Networking {
    static let baseURL = "http://15.164.72.21:8080/grabMe"
    //static let baseURL = "http://192.168.1.206:8080/web/"
   
    enum RequestType: String {
        case get = "GET", post = "POST", put = "PUT", patch = "PATCH", delete = "DELETE"
    }
    
    enum SessionTaskType: String {
        case data, upload, download
    }
    
    // MARK: - Content-Type header
    public enum ParameterType {
        case none, json, formURLEncoded, multipartFormData, custom(String)
        
        func contentType(_ boundary: String) -> String? {
            switch self {
            case .none: return nil
            case .json: return "application/json; charset=UTF-8"
            case .formURLEncoded: return "application/x-www-form-urlencoded"
            case .multipartFormData: return "multipart/form-data; boundary=\(boundary)"
            case let .custom(value): return value
            }
        }
    }
    
    enum ResponseType {
        case json, data, image
        
        var accpet: String? {
            switch self {
            case .json: return "application/json"
            default: return nil
            }
        }
    }
    
    enum StatusCodeType {
        case informational, successful, redirection, clientError, serverError, cancelled, unknown
    }
    
    enum EndPoint {
        case checkUserStatus
        case signUp
        case categoryList(x: String, y: String, categoryIdx: String, page: String)
        case shopDetail(shopIdx: String)
        //(x: String, y: String, categoryIdx: String, page: String)
        
        var url: String {
            switch self {
            case .checkUserStatus: return "\(Networking.baseURL)/sign/check"
            case .signUp: return "\(Networking.baseURL)/sign/up"
            case .categoryList:
                return "\(Networking.baseURL)/category?"
            case .shopDetail: return "\(Networking.baseURL)/shop?"
            }
        }
        
        
        var queryItem: [URLQueryItem] {
            var queryItems: [URLQueryItem] = []
            switch self {
            case .categoryList(let x, let y, let categoryIdx, let page): queryItems = [
                URLQueryItem(name: "x", value: x),
                URLQueryItem(name: "y", value: y),
                URLQueryItem(name: "categoryIdx", value: categoryIdx),
                URLQueryItem(name: "startNum", value: page)
                ]
            case .shopDetail(let shopIdx): queryItems = [
                URLQueryItem(name: "shopIdx", value: shopIdx)
                ]
                
            default:
                return queryItems
            }
            return queryItems
        }
    }
    
    static func requestObject(type: EndPoint, data: Data? = nil, requestType: RequestType) -> URLRequest {
    
        var url = URL(string: type.url)!
        
        if requestType == .get {
            var urlComponents = URLComponents(string: type.url )
            urlComponents?.queryItems?.append(contentsOf: type.queryItem)
            print("🟦\(urlComponents?.url)")
            url = (urlComponents?.url)!
        }
        
        //이거 고쳐야함.. !
        var request = URLRequest(url: url)
        
        if requestType == .post {
            request.httpBody = data
            request.httpMethod = "POST"
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        }

        return request
    }
}
//extension EndPointss {
//    static func categoryList(x: String, y: String, categoryIdx: String, page: String) -> EndPointss {
//        return EndPointss(path: Networking.baseURL, queryItems: [
//                URLQueryItem(name: "x", value: x),
//                URLQueryItem(name: "y", value: y),
//                URLQueryItem(name: "category_idx", value: categoryIdx),
//                URLQueryItem(name: "page", value: page)
//        ])
//    }
//}
//
//extension EndPointss {
//    var url: URL? {
//        var components = URLComponents()
//        components.queryItems = queryItems
//        components.path = path
//        return components.url
//    }
//}
//
//struct EndPointss {
//    let path: String
//    let queryItems: [URLQueryItem]
//}


extension Int {
    var statusCodeType: Networking.StatusCodeType {
        switch self {
        case URLError.cancelled.rawValue: return .cancelled
        case 100 ..< 200: return .informational
        case 200 ..< 300: return .successful
        case 300 ..< 400: return .redirection
        case 400 ..< 500: return .clientError
        case 500 ..< 600: return .serverError
        default: return .unknown
        }
    }
}
