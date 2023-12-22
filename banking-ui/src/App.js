import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import { routes } from './routes';

const App = () => {
  return (
    <>
      <Navbar />
      <div className='container'>
        <Routes>
          {routes.map(r => (
            <Route exact path={r.path} element={r.component} />
          ))}
        </Routes>
      </div>
    </>
  );
};

export default App;
