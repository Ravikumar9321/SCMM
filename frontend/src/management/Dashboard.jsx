import React from "react";
import { Link } from "react-router-dom";

function Dashboard() {
  const stats = [
    { label: "Total Suppliers", value: "247", trend: "+12%" },
    { label: "Products", value: "1,342", trend: "+8%" },
    { label: "Active Orders", value: "89", trend: "-3%" },
    { label: "Customers", value: "456", trend: "+15%" }
  ];

  return (
    <div style={styles.container}>
      <div style={styles.header}>
        <h1 style={styles.title}>Dashboard</h1>
        <p style={styles.subtitle}>Overview of your supply chain</p>
      </div>

      <div style={styles.statsGrid}>
        {stats.map(({ label, value, trend }, i) => (
          <div key={i} style={styles.statCard}>
            <h3 style={styles.statValue}>{value}</h3>
            <p style={styles.statLabel}>{label}</p>
            <span style={styles.trend}>↑ {trend}</span>
          </div>
        ))}
      </div>

      <div style={styles.quickActions}>
        <Link to="/suppliers" style={styles.actionBtn}>Manage Suppliers</Link>
        <Link to="/products" style={styles.actionBtn}>View Products</Link>
        <Link to="/orders" style={styles.actionBtn}>Check Orders</Link>
        <Link to="/customers" style={styles.actionBtn}>Customer List</Link>
      </div>
    </div>
  );
}

const styles = {
  container: {
    padding: "2rem",
    maxWidth: "1400px",
    margin: "0 auto",
    background: "linear-gradient(135deg, #f8fafc 0%, #e2e8f0 100%)",
    minHeight: "100vh",
    fontFamily: "-apple-system, sans-serif"
  },
  header: {
    marginBottom: "3rem",
    textAlign: "center"
  },
  title: {
    fontSize: "2.5rem",
    fontWeight: "800",
    color: "#142c4c",
    margin: "0 0 0.5rem"
  },
  subtitle: {
    fontSize: "1.2rem",
    color: "#64748b",
    margin: 0
  },
  statsGrid: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
    gap: "1.5rem",
    marginBottom: "3rem"
  },
  statCard: {
    background: "white",
    padding: "2rem",
    borderRadius: "16px",
    textAlign: "center",
    boxShadow: "0 8px 30px rgba(20,44,76,0.1)",
    border: "1px solid rgba(112,125,169,0.1)"
  },
  statValue: {
    fontSize: "2.5rem",
    fontWeight: "800",
    color: "#142c4c",
    margin: "0 0 0.25rem"
  },
  statLabel: {
    fontSize: "1rem",
    color: "#64748b",
    margin: "0 0 0.5rem",
    fontWeight: "500"
  },
  trend: {
    fontSize: "0.9rem",
    color: "#10b981",
    fontWeight: "600"
  },
  quickActions: {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))",
    gap: "1rem"
  },
  actionBtn: {
    padding: "1rem 1.5rem",
    background: "white",
    color: "#1e293b",
    textDecoration: "none",
    borderRadius: "12px",
    fontWeight: "600",
    boxShadow: "0 4px 20px rgba(20,44,76,0.1)",
    textAlign: "center",
    transition: "all 0.3s ease",
    border: "1px solid rgba(112,125,169,0.2)"
  }
};

export default Dashboard;
