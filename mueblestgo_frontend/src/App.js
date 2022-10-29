import './App.css';
import { Routes, Route } from "react-router-dom";
import Layout from './Layout';
import { Container } from 'react-bootstrap';
import Index from './pages/Index';
import UploadFile from './pages/UploadFile';

function App() {
  return (
    <Layout>
      <Container>
        <Routes>
          <Route path='/' element={<Index/>} exact/>
          <Route path='/upload' element={<UploadFile/>} exact/>
        </Routes>
      </Container>
    </Layout>
  );
}

export default App;
