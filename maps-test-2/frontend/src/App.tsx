import { useState } from 'react';
import './App.css';
import MapContainer from './MapContainer';

function App() {
  const [theme, setTheme] = useState<'light' | 'dark'>('light');

  const toggleTheme = () => {
    setTheme(prev => prev === 'light' ? 'dark' : 'light');
  };

  return (
    <div className="app-container">
      <header className="header">
        <h1>Bolivia GIS Map (Cochabamba)</h1>
        <button className="theme-toggle" onClick={toggleTheme}>
          Switch to {theme === 'light' ? 'Dark' : 'Light'} Theme
        </button>
      </header>
      <MapContainer theme={theme} />
    </div>
  );
}

export default App;
