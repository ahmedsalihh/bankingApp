import { Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import ListUsers from './containers/ListUser';
import AddUser from './containers/AddUser';

const App = () => {
  return (
    <>
      <Navbar />
      <div className='container'>
        <Routes>
          <Route exact path='/' element={<ListUsers />} />
          <Route exact path='/addUser' element={<AddUser />} />
        </Routes>
      </div>
    </>
  );
};

export default App;
