//
//  CategoryListViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/01.
//

import UIKit

class CategoryListViewController: UIViewController {

    //MARK: - Properties
    private let identifier = "ListCell"

    let list = ["one","two","three","four","five"]
    
    
    //MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}


extension CategoryListViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        list.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: identifier, for: indexPath) as? CategoryListTableViewCell else { return UITableViewCell() }
        
        return cell
        
    }
    
    
}
