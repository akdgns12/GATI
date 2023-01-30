import AddCircleIcon from '@mui/icons-material/AddCircle';
import { useNavigate } from 'react-router';

export default function AddButton() {
  const navigate = useNavigate();
  function writeArticle() {
    navigate('/post');
  };

  return (
    <AddCircleIcon
      onClick={writeArticle}
      color='primary'
      style={{
        zIndex: "1000", width: "80px", height: "80px",
        position: "fixed", right: "20px", bottom: "60px",
      }}
    />
  );
}