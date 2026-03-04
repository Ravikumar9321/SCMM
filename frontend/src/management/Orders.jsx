import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Orders() {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    const getOrders = async () => {
        try {
            setLoading(true);
            const response = await axios.get("http://localhost:8080/api/orders");
            setOrders(response.data.data || response.data);
        } catch (error) {
            alert("Failed to load orders");
            console.error("Orders fetch error:", error);
        } finally {
            setLoading(false);
        }
    };

    const deleteOrder = async (id) => {
        if (window.confirm(`Are you sure you want to delete this order?`)) {
            try {
                await axios.delete(`http://localhost:8080/api/orders/${id}`);
                setOrders(orders.filter(o => o.id !== id));
            } catch (error) {
                alert("Failed to delete order");
                console.error("Delete error:", error);
            }
        }
    };

    const addOrder = () => {
        navigate('/add-order');
    };

    useEffect(() => {
        getOrders();
    }, []);

    if (loading) return <div style={styles.loading}>Loading orders...</div>;

    return (
        <div style={styles.container}>
            <div style={styles.header}>
                <h1 style={styles.title}>Orders ({orders.length})</h1>
                <div style={styles.buttonGroup}>
                    <button style={styles.addBtn} onClick={addOrder}>➕ Add Order</button>
                    <button style={styles.backBtn} onClick={() => navigate("/")}>🏠 Home</button>
                </div>
            </div>
            
            <div style={styles.tableContainer}>
                <table style={styles.table}>
                    <thead>
                        <tr>
                            <th style={styles.th}>ID</th>
                            <th style={styles.th}>Customer</th>
                            <th style={styles.th}>Total</th>
                            <th style={styles.th}>Status</th>
                            <th style={styles.th}>Tracking</th>
                            <th style={styles.th}>Date</th>
                            <th style={styles.th}>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map(order => (
                            <tr key={order.id} style={styles.tr}>
                                <td style={styles.td}>
                                    <span style={styles.orderId}>#{order.id}</span>
                                </td>
                                <td style={styles.td}>
                                    <span style={styles.customerName}>{order.customer?.name || 'N/A'}</span>
                                </td>
                                <td style={styles.td}>
                                    <span style={styles.totalAmount}>₹{order.totalAmount?.toLocaleString()}</span>
                                </td>
                                <td style={styles.td}>
                                    <span style={styles[order.status] || styles.defaultStatus}>
                                        {order.status}
                                    </span>
                                </td>
                                <td style={styles.td}>
                                    <span style={styles.tracking}>{order.trackingNumber || 'N/A'}</span>
                                </td>
                                <td style={styles.td}>
                                    <span style={styles.orderDate}>
                                        {new Date(order.orderDate).toLocaleDateString('en-IN')}
                                    </span>
                                </td>
                                <td style={styles.td}>
                                    <button 
                                        style={styles.deleteBtn} 
                                        onClick={() => deleteOrder(order.id)}
                                    >
                                        🗑️ Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

// PERFECT MATCH - Same styles as Products/Suppliers
const styles = {
    container: { 
        padding: "2rem", 
        maxWidth: "1400px", 
        margin: "0 auto", 
        background: "linear-gradient(135deg, #e2e8f0 0%, #f8fafc 100%)", 
        minHeight: "100vh", 
        fontFamily: "-apple-system, sans-serif" 
    },
    header: { 
        display: "flex", 
        justifyContent: "space-between", 
        alignItems: "center", 
        marginBottom: "2rem",
        flexWrap: "wrap",
        gap: "1rem"
    },
    title: { 
        fontSize: "2.2rem", 
        fontWeight: "800", 
        color: "#1e293b", 
        margin: 0,
        background: "linear-gradient(135deg, #1e40af, #3b82f6)",
        WebkitBackgroundClip: "text",
        WebkitTextFillColor: "transparent"
    },
    buttonGroup: {
        display: "flex",
        gap: "1rem"
    },
    addBtn: { 
        padding: "12px 24px", 
        background: "linear-gradient(135deg, #10b981, #059669)", 
        color: "white", 
        border: "none", 
        borderRadius: "12px", 
        fontWeight: "600", 
        fontSize: "1rem",
        cursor: "pointer", 
        boxShadow: "0 4px 20px rgba(16,185,129,0.3)",
        transition: "all 0.3s ease"
    },
    backBtn: {
        padding: "12px 24px",
        background: "linear-gradient(135deg, #6b7280, #4b5563)",
        color: "white",
        border: "none",
        borderRadius: "12px",
        fontWeight: "600",
        fontSize: "1rem",
        cursor: "pointer",
        boxShadow: "0 4px 20px rgba(107,114,128,0.3)",
        transition: "all 0.3s ease"
    },
    tableContainer: { 
        background: "white", 
        borderRadius: "20px", 
        boxShadow: "0 20px 60px rgba(0,0,0,0.1)", 
        overflow: "hidden" 
    },
    table: { 
        width: "100%", 
        borderCollapse: "collapse" 
    },
    th: { 
        padding: "1.25rem 1rem", 
        textAlign: "left", 
        background: "linear-gradient(135deg, #1e40af, #3b82f6)", 
        fontWeight: "700", 
        color: "white", 
        fontSize: "0.95rem",
        borderBottom: "4px solid #1e3a8a"
    },
    tr: { 
        transition: "all 0.2s ease",
        borderBottom: "2px solid #f1f5f9"
    },
    td: { 
        padding: "1.25rem 1rem", 
        verticalAlign: "middle" 
    },
    orderId: {
        fontWeight: "600",
        color: "#1e40af",
        fontSize: "1.05rem"
    },
    customerName: {
        fontWeight: "700",
        color: "#1e293b"
    },
    totalAmount: {
        fontWeight: "700",
        color: "#dc2626",
        fontSize: "1.1rem"
    },
    tracking: {
        fontFamily: "monospace",
        color: "#059669",
        fontWeight: "600"
    },
    orderDate: {
        color: "#6b7280",
        fontWeight: "500"
    },
    // Status badges - Enhanced
    PENDING: { 
        color: "#d97706", 
        background: "rgba(254,243,199,0.8)", 
        padding: "6px 12px", 
        borderRadius: "20px", 
        fontSize: "0.85rem",
        fontWeight: "600",
        boxShadow: "0 2px 8px rgba(217,119,6,0.2)"
    },
    SHIPPED: { 
        color: "#059669", 
        background: "rgba(209,250,229,0.8)", 
        padding: "6px 12px", 
        borderRadius: "20px", 
        fontSize: "0.85rem",
        fontWeight: "600",
        boxShadow: "0 2px 8px rgba(5,150,105,0.2)"
    },
    DELIVERED: { 
        color: "#10b981", 
        background: "rgba(209,250,229,0.8)", 
        padding: "6px 12px", 
        borderRadius: "20px", 
        fontSize: "0.85rem",
        fontWeight: "600",
        boxShadow: "0 2px 8px rgba(16,185,129,0.2)"
    },
    defaultStatus: { 
        color: "#6b7280", 
        background: "rgba(243,244,246,0.8)", 
        padding: "6px 12px", 
        borderRadius: "20px", 
        fontSize: "0.85rem",
        fontWeight: "600"
    },
    deleteBtn: { 
        padding: "10px 20px", 
        background: "linear-gradient(135deg, #ef4444, #dc2626)", 
        color: "white", 
        border: "none", 
        borderRadius: "10px", 
        fontSize: "0.95rem", 
        fontWeight: "600",
        cursor: "pointer",
        boxShadow: "0 4px 15px rgba(239,68,68,0.4)",
        transition: "all 0.3s ease",
        minWidth: "80px"
    },
    loading: { 
        textAlign: "center", 
        padding: "4rem", 
        fontSize: "1.5rem", 
        color: "#64748b",
        background: "rgba(248,250,252,0.8)",
        borderRadius: "16px",
        boxShadow: "0 10px 30px rgba(0,0,0,0.1)"
    }
};

export default Orders;
