import './App.css';
import { Routes, Route } from "react-router-dom";
import Layout from './Layout';
import { Container } from 'react-bootstrap';
import Index from './pages/Index';
import UploadFile from './pages/UploadFile';
import JustificativeForm from './pages/JustificativeForm';

function App() {
  return (
    <Layout>
      <Container>
        <Routes>
          <Route path='/' element={<Index/>} exact/>
          <Route path='/upload' element={<UploadFile/>} exact/>
          <Route path='/justificatives' element={<JustificativeForm/>} exact/>
        </Routes>
      </Container>
    </Layout>
  );
}

export default App;
