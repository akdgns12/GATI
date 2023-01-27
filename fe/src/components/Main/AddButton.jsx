import AddCircleIcon from '@mui/icons-material/AddCircle';

export default function AddButton() {

  function writeArticle() {
    console.log("move to write article page");
    alert("move page")
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