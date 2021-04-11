//
//  CategoryListDatailViewController.swift
//  GrabMe
//
//  Created by doyeon kim on 2021/04/11.
//

import UIKit
import Kingfisher

class CategoryListDetailViewController: UIViewController {
    //MARK: - Properties
    private var list = ["hello", "two", "three", "four"]
    private var dateIdentifier = "date"
    private var viewModel = CategoryListViewModel()
    @IBOutlet weak var placeImage: UIImageView!
    @IBOutlet weak var placeTitle: UILabel!
    @IBOutlet weak var placeLocation: UILabel!
    @IBOutlet weak var placePhone: UILabel!
    @IBOutlet weak var placeDescription: UILabel!
    
    //MARK: - LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
        
    }
    
    //MARK: - Helpers
    private func configureUI(){
        placeTitle.text = viewModel.categoryDetail.title
        placeLocation.text = viewModel.categoryDetail.address
        placePhone.text = viewModel.categoryDetail.phone
        placeDescription.text = viewModel.categoryDetail.description
        let url = URL(string: viewModel.categoryDetail.thumbnail)
        placeImage.kf.setImage(with: url)
    }
    
    //MARK: - Actions
    @IBAction func backButtonTapped(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
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
