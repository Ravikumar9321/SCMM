import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/api";

function Customers() {
    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const [error, setError] = useState(null);


    const fetchCustomers = async () => {
        try {
            setLoading(true);
            setError(null);
            const response = await api.get("http://localhost:8080/api/customer");
            setCustomers(response.data.data || response.data);

        } catch (error) {
           setError(error.response?.message);
        } finally {
            setLoading(false);
        }
    };

    const addCustomer = () => {
        navigate('/add-customer');
    };

    const deleteCustomer = async (customer) => {
        if (window.confirm(`Are you sure you want to delete "${customer.name}"?`)) {
            try {
                await api.delete(`http://localhost:8080/api/customer/${customer.id}`);
                setCustomers(customers.filter(c => c.id !== customer.id));
            } catch (error) {
           setError(error.response.data?.message);
            }
        }
    };

    useEffect(() => {
        fetchCustomers();
    }, []);

    if (loading) return <div style={styles.loading}>Loading customers...</div>;
return (
    <div style={styles.container}>
        <div style={styles.header}>
            <h1 style={styles.title}>Customers ({customers.length})</h1>
            <div style={styles.buttonGroup}>
                <button style={styles.addBtn} onClick={addCustomer}>➕ Add Customer</button>
                <button style={styles.backBtn} onClick={() => navigate("/")}>🏠 Home</button>
            </div>
        </div>

        {customers.length === 0 ? (
            <div style={styles.emptyState}>
                <div style={styles.emptyIcon}>📭</div>
                <h3 style={styles.emptyTitle}>{error}</h3>
                <p style={styles.emptyText}>Start by adding your first customer!</p>
            </div>
        ) : (
            <div style={styles.tableContainer}>
                <table style={styles.table} border="3px">
                    <thead>
                        <tr>
                            <th style={styles.th}>ID</th>
                            <th style={styles.th}>Name</th>
                            <th style={styles.th}>Email</th>
                            <th style={styles.th}>Contact</th>
                            <th style={styles.th}>Address</th>
                            <th style={styles.th}>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {customers.map(customer => (
                            <tr key={customer.id} style={styles.tr}>
                                <td style={styles.td}><span style={styles.customerId}>{customer.id}</span></td>
                                <td style={styles.td}><span style={styles.customerName}>{customer.name}</span></td>
                                <td style={styles.td}><span style={styles.email}>{customer.email}</span></td>
                                <td style={styles.td}><span style={styles.contact}>{customer.contact}</span></td>
                                <td style={styles.td}><span style={styles.address}>{customer.address}</span></td>
                                <td style={styles.td}>
                                    <button style={styles.deleteBtn} onClick={() => deleteCustomer(customer)}>
                                        🗑️ Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        )}
    </div>
);
}


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
    customerId: {
        fontWeight: "600",
        color: "#1e40af",
        fontSize: "1.05rem"
    },
    customerName: {
        fontWeight: "700",
        color: "#1e293b",
        fontSize: "1.05rem"
    },
    email: {
        color: "#3b82f6",
        fontWeight: "500",
        fontSize: "0.95rem"
    },
    contact: {
        fontFamily: "monospace",
        color: "#059669",
        fontWeight: "600"
    },
    address: {
        color: "#6b7280",
        fontWeight: "500",
        lineHeight: "1.4"
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
    },
    emptyState: {
    textAlign: "center",
    padding: "4rem 2rem",
    background: "linear-gradient(135deg, #f8fafc 0%, #e2e8f0 100%)",
    borderRadius: "20px",
    border: "3px dashed #cbd5e1",
    margin: "2rem 0",
    maxWidth: "600px",
    marginLeft: "auto",
    marginRight: "auto"
},
emptyIcon: {
    fontSize: "4rem",
    marginBottom: "1rem"
},
emptyTitle: {
    fontSize: "1.8rem",
    color: "#475569",
    marginBottom: "0.5rem",
    fontWeight: "700"
},
emptyText: {
    fontSize: "1.1rem",
    color: "#64748b",
    marginBottom: "2rem"
}
};

export default Customers;
        