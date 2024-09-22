import React from "react";


interface CategoryProps {
    category: string
}

const Category:React.FC<CategoryProps> = ({category}) => {
    return <div className="category">
    <p>Category : {category}</p>
    </div>
}
export default Category;