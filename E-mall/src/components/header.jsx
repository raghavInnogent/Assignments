export default function Header() {
  return (
    <header style={styles.header}>
      <h1 style={styles.title}>🛒 E-MALL</h1>

      <div style={styles.icons}>
        <span style={styles.icon} title="Notifications" onClick={() => alert("Notifications clicked!")}>🔔</span>
        <span style={styles.icon} title="Cart" onClick={() => alert("Cart clicked!")}>🛒</span>
        <span style={styles.icon} title="Profile" onClick={() => alert("Profile clicked!")}>👤</span>
      </div>
    </header>
  );
}

const styles = {
  header: {
    backgroundColor: '#000',
    padding: '16px 32px',
    color: 'white',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    boxShadow: '0 2px 6px rgba(0,0,0,0.3)',
    position: 'sticky',
    top: 0,
    zIndex: 1000,
  },
  title: {
    margin: 0,
    fontSize: '1.8rem',
    letterSpacing: '1px',
    fontWeight: '600',
    display: 'flex',
    alignItems: 'center',
    gap: '8px',
  },
  icons: {
    display: 'flex',
    alignItems: 'center',
    gap: '20px',
    fontSize: '1.5rem',
    cursor: 'pointer',
  },
  icon: {
    transition: 'transform 0.2s',
  },
};
