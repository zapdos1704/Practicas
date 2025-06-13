function updateCart() {
    const updatedCart = cart.filter(item => item.quantity > 0);
    setCart(updatedCart);
    calculateTotal(updatedCart);
}
