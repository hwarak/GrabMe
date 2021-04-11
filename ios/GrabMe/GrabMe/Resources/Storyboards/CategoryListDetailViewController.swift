//
//  CategoryListDatailViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/11.
//

import UIKit

class CategoryListDetailViewController: UIViewController {
    //MARK: - Properties
    private var list = ["hello", "two", "three", "four"]
    private var dateIdentifier = "date"
    
    //MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()

    }

}

extension CategoryListDetailViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return list.count + 5
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: dateIdentifier, for: indexPath) as? CategoryDetailDateCollectionViewCell else { return UICollectionViewCell() }
        
        return cell
    }
    
    
}
