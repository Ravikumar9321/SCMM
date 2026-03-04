import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function ProductForm() {
    const [product, setProduct] = useState({
        name: "",
        stockQuantity: "",  
        price: "",
        supplierId: ""          
    });
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {    
        e.preventDefault();
        try {
            setLoading(true);
            // ✅ FIXED: Correct URL + pass supplierId as path param
            await axios.post(`http://localhost:8080/api/product/${product.supplierId}`, {
                name: product.name,
                stockQuantity: parseInt(product.stockQuantity),  
                price: parseFloat(product.price)                
            });
            alert("Product added successfully!");
            navigate('/products');
        } catch (error) {
            alert("Failed to add product: " + error.response?.data?.message || error.message);
        } finally {
            setLoading(false);
        }
    };
   

    return (
        <div style={styles.container}>
            <div style={styles.formCard}>
                <h1 style={styles.title}>Add New Product</h1>
                <form onSubmit={handleSubmit}>
                    <input
                        style={styles.input}
                        name="name"
                        placeholder="Product Name *"
                        value={product.name}
                        onChange={handleChange}
                        required
                    />
                    <input
                        style={styles.input}
                        name="stockQuantity"           // ✅ FIXED: stockQuantity
                        type="number"
                        placeholder="Stock Quantity *"
                        value={product.stockQuantity}
                        onChange={handleChange}
                        required
                    />
                    <input
                        style={styles.input}
                        name="price"
                        type="number"
                        step="0.01"
                        placeholder="Price *"
                        value={product.price}
                        onChange={handleChange}
                        required
                    />
                    <input
                        style={styles.input}
                        name="supplierId"               // ✅ FIXED: supplierId
                        type="number"
                        placeholder="Supplier ID *"
                        value={product.supplierId}
                        onChange={handleChange}
                        required
                    />
                    
                    <div style={styles.buttonGroup}>
                        <button 
                            type="submit" 
                            style={styles.submitBtn}
                            disabled={loading}
                        >
                            {loading ? "Adding..." : "Add Product"}
                        </button>
                        <button 
                            type="button" 
                            style={styles.cancelBtn}
                            onClick={() => navigate('/products')}
                        >
                            Cancel
                        </button>
                        
                    </div>
                </form>
            </div>
        </div>
    );
}
const styles = {
    container: {
        minHeight: "100vh",
        padding: "2rem",
        background: "linear-gradient(135deg, #e2e8f0 0%, #e2e8f0 100%)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center"
    },
    formCard: {
        background: "white",
        padding: "3rem",
        borderRadius: "20px",
        boxShadow: "0 20px 60px rgba(20,44,76,0.15)",
        width: "100%",
        maxWidth: "500px"
    },
    title: {
        fontSize: "2rem",
        fontWeight: "700",
        color: "#142c4c",
        marginBottom: "2rem",
        textAlign: "center"
    },
    input: {
        width: "100%",
        padding: "14px 16px",
        marginBottom: "1.5rem",
        border: "2px solid #e5e7eb",
        borderRadius: "12px",
        fontSize: "1rem",
        outline: "none",
        transition: "border-color 0.3s, box-shadow 0.3s",
        background: "#fafbff"
    },
    buttonGroup: {
        display: "flex",
        gap: "1rem",
        marginTop: "1rem"
    },
    submitBtn: {
        flex: 1,
        padding: "14px",
        background: "linear-gradient(135deg, #10b981, #059669)",
        color: "white",
        border: "none",
        borderRadius: "12px",
        fontSize: "1rem",
        fontWeight: "600",
        cursor: "pointer"
    },
    cancelBtn: {
        padding: "14px 24px",
        background: "#6b7280",
        color: "white",
        border: "none",
        borderRadius: "12px",
        fontWeight: "600",
        cursor: "pointer"
    }
};

export default ProductForm;
