//
//  CategoryListViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import UIKit
import CoreLocation

class CategoryListViewController: UIViewController {

    //MARK: - Properties
    private let identifier = "ListCell"
    private var viewModel = CategoryListViewModel()
    private var locationManager = CLLocationManager()
    var category = ""
    @IBOutlet weak var categoryName: UILabel!
    
    
    //MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        categoryName.text = category
    }
    
    //MARK: - Helpers

}


extension CategoryListViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return viewModel.categoryList.count
       
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: identifier, for: indexPath) as? CategoryListTableViewCell else { return UITableViewCell() }
        cell.configureUI(viewModel.categoryList[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let vc = self.storyboard?.instantiateViewController(identifier: "CategoryDetail") as! CategoryListDetailViewController
        vc.modalPresentationStyle = .fullScreen
        
        let shopIdx = viewModel.categoryList[indexPath.row].shopIdx
        
        viewModel.requestPlaceDetail(shopIdx: "\(shopIdx)", completion: {
            DispatchQueue.main.async {
                self.navigationController?.pushViewController(vc, animated: true)
            }
        })
    }
}

//MARK: - Actions
extension CategoryListViewController {
    @IBAction func popButtonTapped(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
}
