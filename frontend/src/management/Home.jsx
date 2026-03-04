import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();
  const [stats] = useState({ suppliers: 24, products: 156, orders: 89, customers: 42 });

  const cards = [
    { path: 'suppliers', title: 'Suppliers', desc: 'Manage supplier relationships', icon: '👥', color: '#3b82f6' },
    { path: 'products', title: 'Products', desc: 'Track inventory levels', icon: '📦', color: '#10b981' },
    { path: 'orders', title: 'Orders', desc: 'Process and monitor orders', icon: '📋', color: '#f59e0b' },
    { path: 'customers', title: 'Customers', desc: 'Manage customer profiles', icon: '🛒', color: '#8b5cf6' }
  ];

  // ✨ All Styles Organized Here
  const styles = {
    container: {
      minHeight: '100vh',
      background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
      padding: '2rem',
      fontFamily: '"Inter", -apple-system, sans-serif'
    },
    
    heroSection: {
      textAlign: 'center',
      marginBottom: '4rem'
    },
    
    title: {
      fontSize: '4rem',
      fontWeight: 900,
      background: 'linear-gradient(45deg, #fff, #f0f9ff)',
      WebkitBackgroundClip: 'text',
      WebkitTextFillColor: 'transparent',
      marginBottom: '1.5rem',
      textShadow: '0 4px 20px rgba(255,255,255,0.3)'
    },
    
    subtitle: {
      fontSize: '1.5rem',
      color: 'rgba(255,255,255,0.95)',
      marginBottom: '2rem',
      maxWidth: '600px',
      marginLeft: 'auto',
      marginRight: 'auto',
      lineHeight: 1.6
    },
    
    ctaButton: {
      padding: '1rem 3rem',
      background: 'linear-gradient(45deg, #fff, #f8fafc)',
      color: '#1e293b',
      fontSize: '1.2rem',
      fontWeight: 700,
      border: 'none',
      borderRadius: '50px',
      cursor: 'pointer',
      boxShadow: '0 10px 40px rgba(255,255,255,0.3)',
      transition: 'all 0.3s ease',
      transform: 'translateY(0)'
    },
    
    statsGrid: {
      display: 'grid',
      gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))',
      gap: '2rem',
      marginBottom: '4rem',
      maxWidth: '1200px',
      marginLeft: 'auto',
      marginRight: 'auto'
    },
    
    statCard: {
      background: 'rgba(255,255,255,0.15)',
      backdropFilter: 'blur(20px)',
      padding: '2.5rem 2rem',
      borderRadius: '24px',
      border: '1px solid rgba(255,255,255,0.2)',
      textAlign: 'center',
      transition: 'all 0.4s cubic-bezier(0.4, 0, 0.2, 1)'
    },
    
    statValue: {
      fontSize: '3rem',
      fontWeight: 900,
      color: '#fff',
      marginBottom: '0.5rem',
      textShadow: '0 2px 10px rgba(0,0,0,0.3)'
    },
    
    statLabel: {
      fontSize: '1.1rem',
      color: 'rgba(255,255,255,0.9)',
      fontWeight: 600,
      textTransform: 'capitalize'
    },
    
    cardsGrid: {
      display: 'grid',
      gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
      gap: '2rem',
      maxWidth: '1400px',
      marginLeft: 'auto',
      marginRight: 'auto'
    },
    
    featureCard: {
      background: 'rgba(255,255,255,0.95)',
      borderRadius: '28px',
      padding: '3rem 2.5rem',
      cursor: 'pointer',
      boxShadow: '0 20px 60px rgba(0,0,0,0.15)',
      transition: 'all 0.5s cubic-bezier(0.4, 0, 0.2, 1)',
      border: '1px solid rgba(255,255,255,0.2)'
    },
    
    iconContainer: {
      width: '80px',
      height: '80px',
      borderRadius: '20px',
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      margin: '0 auto 2rem',
      fontSize: '2.5rem',
      boxShadow: '0 10px 30px rgba(0,0,0,0.2)',
      transition: 'all 0.3s ease'
    },
    
    cardTitle: {
      fontSize: '2rem',
      fontWeight: 800,
      color: '#1e293b',
      marginBottom: '1rem',
      textAlign: 'center'
    },
    
    cardDesc: {
      color: '#64748b',
      fontSize: '1.1rem',
      lineHeight: 1.7,
      textAlign: 'center'
    }
  };

  // Mouse handlers for reusability
  const handleButtonHover = (e, isEnter) => {
    if (isEnter) {
      e.target.style.transform = 'translateY(-4px)';
      e.target.style.boxShadow = '0 20px 60px rgba(255,255,255,0.4)';
    } else {
      e.target.style.transform = 'translateY(0)';
      e.target.style.boxShadow = '0 10px 40px rgba(255,255,255,0.3)';
    }
  };

  const handleStatHover = (e, isEnter) => {
    if (isEnter) {
      e.currentTarget.style.transform = 'scale(1.05)';
      e.currentTarget.style.background = 'rgba(255,255,255,0.25)';
    } else {
      e.currentTarget.style.transform = 'scale(1)';
      e.currentTarget.style.background = 'rgba(255,255,255,0.15)';
    }
  };

  const handleCardHover = (e, isEnter) => {
    if (isEnter) {
      e.currentTarget.style.transform = 'translateY(-12px) scale(1.02)';
      e.currentTarget.style.boxShadow = '0 35px 80px rgba(0,0,0,0.25)';
    } else {
      e.currentTarget.style.transform = 'translateY(0) scale(1)';
      e.currentTarget.style.boxShadow = '0 20px 60px rgba(0,0,0,0.15)';
    }
  };

  return (
    <div style={styles.container}>
      {/* Hero Section */}
      <div style={styles.heroSection}>
        <h1 style={styles.title}>SCMM</h1>
        <p style={styles.subtitle}>
          Supply Chain Management Module
        </p>
        <button 
          onClick={() => navigate('/dashboard')}
          style={styles.ctaButton}
          onMouseEnter={(e) => handleButtonHover(e, true)}
          onMouseLeave={(e) => handleButtonHover(e, false)}
        >
          🚀 Launch Dashboard
        </button>
      </div>

      {/* Stats Grid */}
      <div style={styles.statsGrid}>
        {Object.entries(stats).map(([key, value]) => (
          <div 
            key={key} 
            style={styles.statCard}
            onMouseEnter={(e) => handleStatHover(e, true)}
            onMouseLeave={(e) => handleStatHover(e, false)}
          >
            <div style={styles.statValue}>{value}</div>
            <div style={styles.statLabel}>{key}</div>
          </div>
        ))}
      </div>

      {/* Feature Cards */}
      <div style={styles.cardsGrid}>
        {cards.map((card) => (
          <div 
            key={card.path}
            style={styles.featureCard}
            onClick={() => navigate(`/${card.path}`)}
            onMouseEnter={(e) => handleCardHover(e, true)}
            onMouseLeave={(e) => handleCardHover(e, false)}
          >
            <div 
              style={{
                ...styles.iconContainer,
                background: `linear-gradient(135deg, ${card.color}, ${card.color}dd)`
              }}
              onMouseEnter={(e) => e.currentTarget.style.transform = 'rotate(10deg)'}
              onMouseLeave={(e) => e.currentTarget.style.transform = 'rotate(0deg)'}
            >
              {card.icon}
            </div>
            <h3 style={styles.cardTitle}>{card.title}</h3>
            <p style={styles.cardDesc}>{card.desc}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
