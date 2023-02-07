import { React } from "react";
import Card from "@mui/material/Card";
import { Box } from "@mui/material";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import FavoriteIcon from "@mui/icons-material/Favorite";
import BookmarkBorderIcon from "@mui/icons-material/BookmarkBorder";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

import { useNavigate } from "react-router";

export default function ArticleCard(props) {
  const navigate = useNavigate();

  const article = props.article;

  const variant = props.variant == null ? "feed" : props.variant;

  const mvToDetail = () => {
    console.log("move to detail page");
    const url = "/detail/" + article.id;
    navigate(url);
  };

  function showOptions() {
    console.log("show options");
    alert("show options");
  }

  function toggleFav() {
    console.log("add/remove this article to/from favorite");
    alert("toggle icon effects");
  }

  function addToPhotoBook() {
    console.log("add article to Photo Book");
    alert("add article to photo book");
  }

  let bookmark = null;
  if (props.mode === "feed") {
    bookmark = (
      <IconButton aria-label="bookmark" onClick={addToPhotoBook}>
        <BookmarkBorderIcon />
      </IconButton>
    );
  }

  return (
    <Card
      sx={{ borderRadius: 1 }}
      style={{ marginBottom: "10px", width: "100%" }}
    >
      <CardHeader
        action={
          <IconButton aria-label="settings" onClick={showOptions}>
            <MoreHorizIcon />
          </IconButton>
        }
        title={article.userId}
        titleTypographyProps={{ variant: "body1" }}
        style={{ textAlign: "left", padding: "10px" }}
      />
      {article.img != null ? (
        <CardMedia
          component="img"
          width="100%"
          image={article.img}
          alt="No photo"
        />
      ) : (
        "no img"
      )}

      <CardActions disableSpacing={true}>
        <IconButton aria-label="add to favorites" onClick={toggleFav}>
          <FavoriteIcon />
        </IconButton>
        {article.likes}
        <Box style={{ marginLeft: "auto" }}>
          <Typography
            variant="body4"
            style={{ fontWeight: "bold", marginRight: "10px" }}
          >
            {article.createTime.split("T")[0]}
          </Typography>
          {bookmark}
        </Box>
      </CardActions>

      <CardContent>
        <Typography variant="body2" style={{ textAlign: "left" }}>
          {article.content}
        </Typography>
      </CardContent>

      {variant == "feed" ? (
        <CardActions style={{ height: "30px" }}>
          <Typography
            variant="body2"
            onClick={mvToDetail}
            style={{ marginLeft: 10, cursor: "pointer" }}
          >
            Comment &nbsp;
            {article.comment > 0 && article.comment}
          </Typography>
        </CardActions>
      ) : null}
    </Card>
  );
}
