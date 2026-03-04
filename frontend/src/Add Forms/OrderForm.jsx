import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function OrderForm() {
    const [orderData, setOrderData] = useState({
        customerId: "",
        productIds: [], // ✅ Fixed: Array for product IDs
        status: "PENDING",
        trackingNumber: ""
    });
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        setOrderData({ ...orderData, [e.target.name]: e.target.value });
    };

    // ✅ Add product via prompt (simple for fresher)
    const addProduct = () => {
        const productId = prompt("Enter Product ID:");
        if (productId && !isNaN(productId) && !orderData.productIds.includes(productId)) {
            setOrderData({
                ...orderData,
                productIds: [...orderData.productIds, productId]
            });
        }
    };

    // ✅ Remove product
    const removeProduct = (id) => {
        setOrderData({
            ...orderData,
            productIds: orderData.productIds.filter(p => p !== id)
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        // ✅ Validation
        if (!orderData.customerId) {
             if(window.confirm("Not Registered Do you want register")){
               navigate("/add-customer")
             }

                 
            
        }
        if (orderData.productIds.length === 0) {
            alert("Please add at least 1 product!");
            return;
        }

        try {
            setLoading(true);
            await axios.post(`http://localhost:8080/api/orders/${orderData.customerId}`, {
                products: orderData.productIds.map(id => ({ id: parseInt(id) })), // ✅ Fixed!
                status: orderData.status,
                trackingNumber: orderData.trackingNumber || "TRK" + Date.now()
            });
            alert("✅ Order created successfully!");
            navigate('/orders');
        } catch (error) {
            console.error("Error:", error.response?.data);
            alert(`❌ Failed: ${error.response?.data?.message || error.message}`);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={styles.container}>
            <div style={styles.formCard}>
                <h1 style={styles.title}>Create New Order</h1>
                <form onSubmit={handleSubmit}>
                    {/* Customer ID */}
                    <input
                        style={styles.input}
                        name="customerId"
                        type="number"
                        placeholder="Customer ID *"
                        value={orderData.customerId}
                        onChange={handleChange}
                        required
                    />

                    {/* Products Section */}
                    <div style={styles.section}>
                        <label style={styles.sectionLabel}>Products ({orderData.productIds.length})</label>
                        <button type="button" style={styles.addProductBtn} onClick={addProduct}>
                            ➕ Add Product ID
                        </button>
                        
                        <div style={styles.productList}>
                            {orderData.productIds.length === 0 ? (
                                <p style={styles.emptyText}>No products added yet</p>
                            ) : (
                                orderData.productIds.map((id, index) => (
                                    <div key={id} style={styles.productItem}>
                                        <span>Product #{id}</span>
                                        <button 
                                            type="button" 
                                            style={styles.removeBtn} 
                                            onClick={() => removeProduct(id)}
                                        >
                                            ❌ Remove
                                        </button>
                                    </div>
                                ))
                            )}
                        </div>
                    </div>

                    {/* Tracking Number */}
                    <input
                        style={styles.input}
                        name="trackingNumber"
                        placeholder="Tracking Number (optional)"
                        value={orderData.trackingNumber}
                        onChange={handleChange}
                    />

                    {/* Status */}
                    <select style={styles.input} name="status" value={orderData.status} onChange={handleChange}>
                        <option value="PENDING">PENDING</option>
                        <option value="SHIPPED">SHIPPED</option>
                        <option value="DELIVERED">DELIVERED</option>
                    </select>

                    {/* Buttons */}
                    <div style={styles.buttonGroup}>
                        <button 
                            type="submit" 
                            style={styles.submitBtn} 
                            disabled={loading || orderData.productIds.length === 0}
                        >
                            {loading ? "⏳ Creating..." : `✅ Create Order (${orderData.productIds.length} products)`}
                        </button>
                        <button 
                            type="button" 
                            style={styles.cancelBtn} 
                            onClick={() => navigate('/orders')}
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
        maxWidth: "600px" 
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
        background: "#fafbff",
        transition: "border-color 0.3s"
    },
    section: { 
        marginBottom: "1.5rem", 
        padding: "1.5rem", 
        background: "#f8fafc", 
        borderRadius: "12px", 
        border: "1px solid #e2e8f0" 
    },
    sectionLabel: { 
        fontWeight: "600", 
        color: "#1e293b", 
        marginBottom: "1rem", 
        display: "block",
        fontSize: "1.1rem"
    },
    addProductBtn: { 
        padding: "10px 20px", 
        background: "linear-gradient(135deg, #3b82f6, #1d4ed8)", 
        color: "white", 
        border: "none", 
        borderRadius: "10px", 
        fontWeight: "600", 
        cursor: "pointer",
        marginBottom: "1rem"
    },
    productList: { minHeight: "60px" },
    productItem: { 
        display: "flex", 
        justifyContent: "space-between", 
        alignItems: "center", 
        padding: "12px 16px", 
        background: "white", 
        borderRadius: "10px", 
        marginBottom: "0.75rem", 
        border: "1px solid #e5e7eb",
        boxShadow: "0 1px 3px rgba(0,0,0,0.1)"
    },
    removeBtn: { 
        background: "#ef4444", 
        color: "white", 
        border: "none", 
        borderRadius: "6px", 
        padding: "6px 12px", 
        fontWeight: "500", 
        cursor: "pointer",
        fontSize: "0.9rem"
    },
    emptyText: { 
        color: "#64748b", 
        fontStyle: "italic", 
        margin: "1rem 0",
        textAlign: "center"
    },
    buttonGroup: { 
        display: "flex", 
        gap: "1rem", 
        marginTop: "2rem" 
    },
    submitBtn: { 
        flex: 1, 
        padding: "16px", 
        background: "linear-gradient(135deg, #10b981, #059669)", 
        color: "white", 
        border: "none", 
        borderRadius: "12px", 
        fontSize: "1.1rem", 
        fontWeight: "600", 
        cursor: "pointer",
        transition: "all 0.3s"
    },
    cancelBtn: { 
        padding: "16px 28px", 
        background: "#6b7280", 
        color: "white", 
        border: "none", 
        borderRadius: "12px", 
        fontWeight: "600", 
        cursor: "pointer",
        transition: "all 0.3s"
    }
};

export default OrderForm;
