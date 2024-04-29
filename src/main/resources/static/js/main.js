getProducts = () => {
  return fetch("/api/products")
  .then(response => response.json());
}
 
const createProductHtml = (productData) => {
    const template = `
        <div>
            <h4>${productData.name}</h4>
            <span>${productData.price}</span>
            <img src='https://picsum/id/237/200/300'/>
            <button data-id="${productData.id}">Add to cart</button>
        </div>
    `

    const productEl = document.createElement('li');
    productEl.innerHTML = "abc xyz";
 
    return productEl;
}
 
document.addEventListener("DOMContentLoaded", () => {
    const productsListEl = document.querySelector("#productsList");
    getProducts()
        .then(productsAsJsonObj => productsAsJsonObj.map(product => createProductHtml))
        .then(productsAsHtmlEl => {
            productsAsHtmlEl.forEach(productEl => productsListEl.appendChild(productEl));
        })
});

console.log("asd")